
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
 *         &lt;element name="ActionGroup_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ActionGroup_Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Predicate_Configuration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Server_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Server_Variable_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "actionGroupName",
    "actionGroupDescription",
    "predicateConfiguration",
    "serverId",
    "serverVariableName"
})
@XmlRootElement(name = "Plans_UpdateActionGroup")
public class PlansUpdateActionGroup {

    protected String apiKey;
    @XmlElement(name = "ActionGroup_Id", required = true, type = Integer.class, nillable = true)
    protected Integer actionGroupId;
    @XmlElement(name = "ActionGroup_Name")
    protected String actionGroupName;
    @XmlElement(name = "ActionGroup_Description")
    protected String actionGroupDescription;
    @XmlElement(name = "Predicate_Configuration")
    protected String predicateConfiguration;
    @XmlElement(name = "Server_Id", required = true, type = Integer.class, nillable = true)
    protected Integer serverId;
    @XmlElement(name = "Server_Variable_Name")
    protected String serverVariableName;

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
     * Gets the value of the actionGroupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionGroupName() {
        return actionGroupName;
    }

    /**
     * Sets the value of the actionGroupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionGroupName(String value) {
        this.actionGroupName = value;
    }

    /**
     * Gets the value of the actionGroupDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionGroupDescription() {
        return actionGroupDescription;
    }

    /**
     * Sets the value of the actionGroupDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionGroupDescription(String value) {
        this.actionGroupDescription = value;
    }

    /**
     * Gets the value of the predicateConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPredicateConfiguration() {
        return predicateConfiguration;
    }

    /**
     * Sets the value of the predicateConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPredicateConfiguration(String value) {
        this.predicateConfiguration = value;
    }

    /**
     * Gets the value of the serverId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getServerId() {
        return serverId;
    }

    /**
     * Sets the value of the serverId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setServerId(Integer value) {
        this.serverId = value;
    }

    /**
     * Gets the value of the serverVariableName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerVariableName() {
        return serverVariableName;
    }

    /**
     * Sets the value of the serverVariableName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerVariableName(String value) {
        this.serverVariableName = value;
    }

}
