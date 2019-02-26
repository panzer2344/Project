package com.azino.project.service.impl;

import com.azino.project.constants.Constant;
import com.azino.project.model.Item;
import com.azino.project.model.ShoppingBasket;
import com.azino.project.model.User;
import com.azino.project.repository.ItemRepository;
import com.azino.project.repository.ShoppingBasketRepository;
import com.azino.project.service.ItemService;
import com.azino.project.service.ShoppingBasketService;
import com.azino.project.service.UserService;
import com.azino.project.util.JArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShoppingBasketServiceImpl
        extends BaseServiceImpl<ShoppingBasket, ShoppingBasketRepository>
        implements ShoppingBasketService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    public ShoppingBasketServiceImpl(ShoppingBasketRepository shoppingBasketRepository) {
        super(shoppingBasketRepository);
    }

    @Override
    public ShoppingBasket findByUserName(String username) {
        return super.repository.findByUser_Name(username);
    }

    @Override
    public User findUserBySBId(Long id) {
        return super.repository.findUserByShoppingBasketId(id);
    }

    @Override
    public User findUserBySB(ShoppingBasket sb) {
        return super.repository.findUserByShoppingBasketId(sb.getId());
    }

    @Override
    public BigDecimal findAmountBySBId(Long id) {
        return super.repository.findAmountByShoppingBasketId(id);
    }

    @Override
    public BigDecimal findAmountBySB(ShoppingBasket sb) {
        return super.repository.findAmountByShoppingBasketId(sb.getId());
    }

    @Override
    public List<Item> findItemsInShoppingBasketById(Long id) {
        Iterable<Item> items = super.repository.findItemsInShoppingBasketById(id);
        List<Item> itemList = new ArrayList<>();
        items.forEach(itemList::add);
        return itemList;
    }

    @Override
    public List<Item> findItemsInShoppingBasket(ShoppingBasket sb) {
        return findItemsInShoppingBasketById(sb.getId());
    }

    @Override
    @Transactional
    public void deleteItemFromShoppingBasket(Long itemId, Long shoppingBasketId) {
        //super.repository.deleteItemFromShoppingBasket(itemId, shoppingBasketId);
        Item item = itemRepository.findById(itemId).orElse(new Item());
        item.setCountInStock(item.getCountInStock() + 1);
        List<Item> items = findItemsInShoppingBasketById(shoppingBasketId);
        items.remove(item);
        super.repository
                .findById(shoppingBasketId)
                .get()
                .setItems(items);
    }

    @Override
    @Transactional
    public void deleteAllItemsFromShoppingBasket(Long shoppingBasketId) {
        List<Item> items = findItemsInShoppingBasketById(shoppingBasketId);
        items.forEach(item -> item.setCountInStock(item.getCountInStock() + 1));
        items.clear();
        super.repository
                .findById(shoppingBasketId)
                .get()
                .setItems(items);
    }

    @Override
    public void addToShoppingBasket(User user, Long itemId) {
        Item item = itemRepository.findById(itemId).orElse(new Item());
        item.setCountInStock(item.getCountInStock() - 1);
        ShoppingBasket shoppingBasket = findByUserName(user.getName());
        if (null == shoppingBasket) {
            shoppingBasket = new ShoppingBasket(
                    (long) 0,
                    user,
                    new ArrayList<>(),
                    BigDecimal.ZERO
            );
            shoppingBasket = save(shoppingBasket);
        }
        shoppingBasket.getItems().add(item);
    }

    @Override
    public void addToShoppingBasket(User user, Item item) {
        item.setCountInStock(item.getCountInStock() - 1);
        ShoppingBasket shoppingBasket = findByUserName(user.getName());
        if (null == shoppingBasket) {
            shoppingBasket = new ShoppingBasket(
                    (long) 0,
                    user,
                    new ArrayList<>(),
                    BigDecimal.ZERO
            );
            shoppingBasket = save(shoppingBasket);
        }
        shoppingBasket.getItems().add(item);
    }

    public ShoppingBasket getShoppingBasketFromSession(HttpSession session){
        ShoppingBasket shoppingBasket = (ShoppingBasket) session.getAttribute(Constant.SHOPPING_BASKET_SESSION_NAME);
        if(null != shoppingBasket) {
            List<Item> items = shoppingBasket.getItems();
            List<Item> updatedItems = new ArrayList<>();
            for (Item item : items) {
                Item updatedItem = itemRepository.findById(item.getId()).get();
                updatedItems.add(updatedItem);
            }
            shoppingBasket.setItems(updatedItems);
            session.setAttribute(Constant.SHOPPING_BASKET_SESSION_NAME, shoppingBasket);
        }
        return shoppingBasket;
    }

    @Override
    public Boolean deleteFromShoppingBasketUnlogin(Item item, HttpSession session) {
        return deleteFromShoppingBasketUnlogin(item.getId(), session);
    }

    @Override
    public Boolean deleteFromShoppingBasketUnlogin(Long itemId, HttpSession session) {
        //Item item = itemRepository.findById(itemId).orElse(new Item());
        //item.setCountInStock(item.getCountInStock() - 1);
        //ShoppingBasket shoppingBasket = findByUserName(user.getName());
        //ShoppingBasket shoppingBasket = (ShoppingBasket) session.getAttribute(Constant.SHOPPING_BASKET_SESSION_NAME);
        ShoppingBasket shoppingBasket = getShoppingBasketFromSession(session);

        Optional<Item> item = itemService.findById(itemId);
        if (item.isPresent()) {
            //shoppingBasket = (ShoppingBasket) request.getSession().getAttribute(Constant.SHOPPING_BASKET_SESSION_NAME);
            if (null != shoppingBasket && null != shoppingBasket.getItems()) {
                /*List<Item> items = shoppingBasket.getItems();
                Item item1 = item.get();
                boolean contains = items.contains(item1);*/
                //if (shoppingBasket.getItems().contains(item.get()) || shoppingBasket.getItems().get(0).equals(item.get())) {
                //if(shoppingBasket.getItems().indexOf(item.get()) != -1){
                //if (contains) {
                //if(JArrayUtils.contains(shoppingBasket.getItems(), item.get())){
                if(shoppingBasket.getItems().contains(item.get())){
                    shoppingBasket.getItems().remove(item.get());
                    shoppingBasket.setAmount(shoppingBasket.getAmount().subtract(item.get().getPrice()));
                    item.get().setCountInStock(item.get().getCountInStock() + 1);
                }
                //request.getSession().setAttribute(Constant.SHOPPING_BASKET_SESSION_NAME, shoppingBasket);
                session.setAttribute(Constant.SHOPPING_BASKET_SESSION_NAME, shoppingBasket);
                return true;
            }/*else{
                return HttpStatus.BAD_REQUEST;
            }*/
        }
        return false;
        /*if (null == shoppingBasket) {
            shoppingBasket = new ShoppingBasket(
                    (long) 0,
                    user,
                    new ArrayList<>(),
                    BigDecimal.ZERO
            );
            shoppingBasket = save(shoppingBasket);
        }*/
        //shoppingBasket.getItems().add(item);
    }

    @Override
    public Boolean addToShoppingBasketUnlogin(Item item, HttpSession session){
        ShoppingBasket shoppingBasket = (ShoppingBasket) session.getAttribute(Constant.SHOPPING_BASKET_SESSION_NAME);
        if (shoppingBasket != null) {
            if (shoppingBasket.getItems() == null) {
                shoppingBasket.setItems(new ArrayList<Item>());
            }
        } else {
            shoppingBasket = new ShoppingBasket((long) 0, new com.azino.project.model.User(), new ArrayList<Item>(), BigDecimal.ZERO);
            System.out.println("create shopping basket");
            /*shoppingBasket.getItems().add(item);*/
        }
        shoppingBasket.getItems().add(item);
        shoppingBasket.setAmount(shoppingBasket.getAmount().add(item.getPrice()));
        item.setCountInStock(item.getCountInStock() - 1);
        session.setAttribute(Constant.SHOPPING_BASKET_SESSION_NAME, shoppingBasket);
        return true;
    }

    @Override
    public Boolean addToShoppingBasketUnlogin(Long itemId, HttpSession session) {
        Item item = itemRepository.findById(itemId).get();
        return addToShoppingBasketUnlogin(item, session);
    }

    @Override
    @Transactional
    public ShoppingBasket saveFromUnloginToDatabase(HttpSession session, String username) {
        ShoppingBasket shoppingBasketInDb = findByUserName(username);
        ShoppingBasket shoppingBasketInSession = getShoppingBasketFromSession(session);
        if(shoppingBasketInDb == null){
            shoppingBasketInSession.setUser(userService.findByName(username));
            shoppingBasketInDb = save(shoppingBasketInSession);
        } else{
            List<Item> itemsInSbInDb = shoppingBasketInDb.getItems();
            shoppingBasketInSession.getItems().forEach(i -> {
                Item item = itemService.findById(i.getId()).get();
                itemsInSbInDb.add(item);
            });
            shoppingBasketInDb.setAmount(shoppingBasketInDb.getAmount().add(shoppingBasketInSession.getAmount()));
            //shoppingBasketInDb.getItems()
        }
        return shoppingBasketInDb;
    }
}
