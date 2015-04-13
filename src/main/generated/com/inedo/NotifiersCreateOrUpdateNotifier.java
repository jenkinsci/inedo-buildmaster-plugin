
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
 *         &lt;element name="Notifier_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Notifier_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Notifier_Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Notifier_Configuration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Owner_User_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Active_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EventCodeList_Csv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "notifierId",
    "notifierName",
    "notifierDescription",
    "notifierConfiguration",
    "ownerUserName",
    "activeIndicator",
    "eventCodeListCsv"
})
@XmlRootElement(name = "Notifiers_CreateOrUpdateNotifier")
public class NotifiersCreateOrUpdateNotifier {

    protected String apiKey;
    @XmlElement(name = "Notifier_Id", required = true, type = Integer.class, nillable = true)
    protected Integer notifierId;
    @XmlElement(name = "Notifier_Name")
    protected String notifierName;
    @XmlElement(name = "Notifier_Description")
    protected String notifierDescription;
    @XmlElement(name = "Notifier_Configuration")
    protected String notifierConfiguration;
    @XmlElement(name = "Owner_User_Name")
    protected String ownerUserName;
    @XmlElement(name = "Active_Indicator")
    protected String activeIndicator;
    @XmlElement(name = "EventCodeList_Csv")
    protected String eventCodeListCsv;

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
     * Gets the value of the notifierId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNotifierId() {
        return notifierId;
    }

    /**
     * Sets the value of the notifierId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNotifierId(Integer value) {
        this.notifierId = value;
    }

    /**
     * Gets the value of the notifierName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotifierName() {
        return notifierName;
    }

    /**
     * Sets the value of the notifierName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotifierName(String value) {
        this.notifierName = value;
    }

    /**
     * Gets the value of the notifierDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotifierDescription() {
        return notifierDescription;
    }

    /**
     * Sets the value of the notifierDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotifierDescription(String value) {
        this.notifierDescription = value;
    }

    /**
     * Gets the value of the notifierConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotifierConfiguration() {
        return notifierConfiguration;
    }

    /**
     * Sets the value of the notifierConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotifierConfiguration(String value) {
        this.notifierConfiguration = value;
    }

    /**
     * Gets the value of the ownerUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerUserName() {
        return ownerUserName;
    }

    /**
     * Sets the value of the ownerUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerUserName(String value) {
        this.ownerUserName = value;
    }

    /**
     * Gets the value of the activeIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActiveIndicator() {
        return activeIndicator;
    }

    /**
     * Sets the value of the activeIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActiveIndicator(String value) {
        this.activeIndicator = value;
    }

    /**
     * Gets the value of the eventCodeListCsv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventCodeListCsv() {
        return eventCodeListCsv;
    }

    /**
     * Sets the value of the eventCodeListCsv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventCodeListCsv(String value) {
        this.eventCodeListCsv = value;
    }

}
