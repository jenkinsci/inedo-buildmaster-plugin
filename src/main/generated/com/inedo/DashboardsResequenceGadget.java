
package com.inedo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apiKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Gadget_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="New_Display_Sequence" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "apiKey",
    "gadgetId",
    "newDisplaySequence"
})
@XmlRootElement(name = "Dashboards_ResequenceGadget")
public class DashboardsResequenceGadget {

    protected String apiKey;
    @XmlElement(name = "Gadget_Id", required = true, type = Integer.class, nillable = true)
    protected Integer gadgetId;
    @XmlElement(name = "New_Display_Sequence", required = true, type = Integer.class, nillable = true)
    protected Integer newDisplaySequence;

    /**
     * Gets the value of the apiKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Sets the value of the apiKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApiKey(String value) {
        this.apiKey = value;
    }

    /**
     * Gets the value of the gadgetId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGadgetId() {
        return gadgetId;
    }

    /**
     * Sets the value of the gadgetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGadgetId(Integer value) {
        this.gadgetId = value;
    }

    /**
     * Gets the value of the newDisplaySequence property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNewDisplaySequence() {
        return newDisplaySequence;
    }

    /**
     * Sets the value of the newDisplaySequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNewDisplaySequence(Integer value) {
        this.newDisplaySequence = value;
    }

}
