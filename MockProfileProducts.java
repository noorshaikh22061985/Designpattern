package co.wigroup.order.common.order;

public class MockProfileProducts {

	private String  sku;
    private Integer quantity;
    private String  itemId;
    private Integer  lineNumber;

    /**
     * This function will retrieve the product sku
     *
     * @access public
     * @return String sku
     */
    public String getSku()
    {
        return sku;
    }

    /**
     * This function will set the product sku
     *
     * @access public
     * @param  sku The product sku
     * @return void
     */
    public void setSku(String sku)
    {
        this.sku = sku;
    }

    /**
     * This function will retrieve the item quantity
     *
     * @access public
     * @return Integer quantity
     */
    public Integer getQuantity()
    {
        return quantity;
    }

    /**
     * This function will set the quantity
     *
     * @access public
     * @param  quantity The number of items ordered
     * @return void
     */
    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    /**
     * This function will retrieve the item id
     *
     * @access public
     * @return String itemId
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * This function will set the item id
     *
     * @access public
     * @param  itemId The ordered item id
     * @return void
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * This function will retrieve the line number
     *
     * @access public
     * @return String lineNumber
     */
    public Integer getLineNumber() {
        return lineNumber;
    }

    /**
     * This function will set the line number
     *
     * @access public
     * @param  lineNumber The ordered line number
     * @return void
     */
    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

}
