
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
 *         &lt;element name="ActionGroup_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Action_Sequence" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "actionGroupId",
    "actionSequence"
})
@XmlRootElement(name = "Plans_GetAction")
public class PlansGetAction {

    protected String apiKey;
    @XmlElement(name = "ActionGroup_Id", required = true, type = Integer.class, nillable = true)
    protected Integer actionGroupId;
    @XmlElement(name = "Action_Sequence", required = true, type = Integer.class, nillable = true)
    protected Integer actionSequence;

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
     * Gets the value of the actionGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getActionGroupId() {
        return actionGroupId;
    }

    /**
     * Sets the value of the actionGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setActionGroupId(Integer value) {
        this.actionGroupId = value;
    }

    /**
     * Gets the value of the actionSequence property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getActionSequence() {
        return actionSequence;
    }

    /**
     * Sets the value of the actionSequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setActionSequence(Integer value) {
        this.actionSequence = value;
    }

}
