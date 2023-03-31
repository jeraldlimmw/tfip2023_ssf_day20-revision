package QuotationApp.day20revision.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import QuotationApp.day20revision.model.Item;
import QuotationApp.day20revision.model.Quotation;
import QuotationApp.day20revision.model.ShippingAddress;
import QuotationApp.day20revision.service.QuotationService;
import QuotationApp.day20revision.model.Cart;
import QuotationApp.day20revision.model.Invoice;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class PurchaseOrderController {

    @Autowired
    private QuotationService qSvc;

    private final String[] ITEMS = {"apple", "orange", "bread", "chicken", 
            "cheese", "mineral_water", "instant_noodles"};

    @GetMapping(path={"/", "/index.html"})
    public String showForm (Model m, HttpSession ss) {
        Cart cart = (Cart) ss.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            ss.setAttribute("cart", cart);
            System.out.println(">>>> cart created");
        }

        m.addAttribute("item", new Item());
        m.addAttribute("cart", cart);
        return "view1";
    }

    @PostMapping(path="/cart")
    public String addToCart (Model m, HttpSession ss, 
            @Valid Item item, BindingResult bindings) {

        Cart cart = (Cart) ss.getAttribute("cart");

        if(bindings.hasErrors()) {
            m.addAttribute("item", item);
            m.addAttribute("cart", cart);
            return "view1";
        }

        if (!Arrays.asList(ITEMS).contains(item.getItemName())) {
            FieldError error = new FieldError("item", "itemName", "We do not stock " + item.getItemName());
            bindings.addError(error);
            m.addAttribute("cart", cart);
            return "view1";
        }        

        cart.addItem(item);
        System.out.println(">>>> cart:" + cart.getContents().toString());

        ss.setAttribute("cart", cart);
        m.addAttribute("item", item);
        m.addAttribute("cart", cart);

        return "view1";
    }

    @GetMapping(path="/shippingaddress")
    public String getShipAdd(Model m, HttpSession ss) {
        Cart cart = (Cart) ss.getAttribute("cart");
        if(cart == null || cart.getContents().isEmpty()) {
            m.addAttribute("cart", cart);
            return "view1";
        }

        m.addAttribute("shippingAddress", new ShippingAddress());
        return "view2";
    }

    @PostMapping(path="/checkout")
    public String postShipAdd(Model m, HttpSession ss, 
            @Valid ShippingAddress shippingAddress, BindingResult bindings
            ) throws Exception{
        
        if(bindings.hasErrors()) {
            m.addAttribute("shippingAddress", shippingAddress);
            return "view2";
        }

        Cart cart = (Cart) ss.getAttribute("cart");
        Quotation quotation = new Quotation();
        try {
            quotation = qSvc.getQuotations(cart.getItemList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Invoice invoice = new Invoice(cart, shippingAddress, quotation);
        
        m.addAttribute("invoice", invoice);
        ss.invalidate();
        return "view3";
    }
}
