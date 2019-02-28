package com.azino.project.security.listeners;

import com.azino.project.constants.Constant;
import com.azino.project.model.Item;
import com.azino.project.model.ShoppingBasket;
import com.azino.project.service.ItemService;
import com.azino.project.service.ShoppingBasketService;
import com.azino.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.security.Principal;
import java.util.List;

/*@Component
public class JHttpSessionListener implements ApplicationListener<SessionDestroyedEvent> {

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event)
    {
        for (SecurityContext securityContext : event.getSecurityContexts())
        {
            Authentication authentication = securityContext.getAuthentication();
            Principal user = (Principal) authentication.getPrincipal();
            System.out.println("Hello!");
            System.out.println(((ShoppingBasket)authentication.getPrincipal()));
            // do something
        }
    }

}*/

public class JHttpSessionListener implements HttpSessionListener {

    @Autowired
    private ShoppingBasketService shoppingBasketService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    /*@Autowired
    private SecurityContextHolder securityContextHolder;*/

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("hello! session created!");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* return items to bazar */
        //ShoppingBasket shoppingBasket = (ShoppingBasket) se.getSession().getAttribute(Constant.SHOPPING_BASKET_SESSION_NAME);
        ShoppingBasket shoppingBasket = shoppingBasketService.getShoppingBasketFromSession(se.getSession());
        //List<Item> items = null;
        //Principal principal = (Principal) ((Authentication)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getPrincipal();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if(principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails)principal;
            System.out.println();
            System.out.println();
            System.out.println("<============================================================================>");
            System.out.println("<----------------- Username is: " + userDetails.getUsername() + " ----------->");
            System.out.println("<============================================================================>");
            System.out.println();
            System.out.println();
            /* take session id and remove from user active sessions */
            userService.deleteSessionFromActiveSessions(userDetails.getUsername(), se.getSession().getId());
        }
        if (null != shoppingBasket) {
            /*if(null != shoppingBasket.getUser().getName()){
                items = shoppingBasketService.findByUserName(shoppingBasket.getUser().getName()).getItems();
            }else {
                items = shoppingBasket.getItems();
            }*/
            if (null == shoppingBasket.getUser().getName()) {
            List<Item> items = shoppingBasket.getItems();
                for (Item item : items) {
                    itemService.increaseCountInStock(item.getId(), 1);
                    //Item itemInDB = itemService.findById(item.getId()).get();
                    //itemInDB.setCountInStock(itemInDB.getCountInStock() + 1);
                    //item.setCountInStock(item.getCountInStock() + 1);
                }
            }
        }
        System.out.println("hello! session destroyed!");
    }
}
