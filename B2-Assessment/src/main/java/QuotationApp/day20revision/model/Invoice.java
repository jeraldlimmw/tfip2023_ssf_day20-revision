package QuotationApp.day20revision.model;

import java.util.List;

public class Invoice {
    private ShippingAddress shipAdd;
    private String invoiceId;
    private double total;

    public Invoice(Cart cart, ShippingAddress shipAdd, Quotation quotation) {
        this.shipAdd = shipAdd;
        this.invoiceId = quotation.getQuoteId();
        this.total = getTotal(cart.getContents(), quotation.getQuotations());
    }

    public ShippingAddress getShipAdd() {
        return shipAdd;
    }
    public void setShipAdd(ShippingAddress shipAdd) {
        this.shipAdd = shipAdd;
    }
    public String getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    public String getAddress() {
        return shipAdd.getAddress();
    }

    public String getName() {
        return shipAdd.getName();
    }

    public double getTotal(List<Item> items, List<Quote> quotations) {
        double total = 0;
        for (Item i : items) {
            double unitPrice = get(i.getItemName(), quotations).getUnitPrice();
            total += (i.getQuantity() * unitPrice);
        }
        return total;
    }

    public Quote get(String itemName, List<Quote> quotations) {
        int index = -1;
        for (Quote q : quotations) {
            if (q.getItemName().equalsIgnoreCase(itemName)) {
                index = quotations.indexOf(q);
            } 
        }
        return quotations.get(index);
    }
}
