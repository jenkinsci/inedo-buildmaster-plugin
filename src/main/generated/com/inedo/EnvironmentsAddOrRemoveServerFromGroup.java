
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
 *         &lt;element name="Server_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ServerGroup_Server_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="AddOrRemove_Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "serverId",
    "serverGroupServerId",
    "addOrRemoveCode"
})
@XmlRootElement(name = "Environments_AddOrRemoveServerFromGroup")
public class EnvironmentsAddOrRemoveServerFromGroup {

    protected String apiKey;
    @XmlElement(name = "Server_Id", required = true, type = Integer.class, nillable = true)
    protected Integer serverId;
    @XmlElement(name = "ServerGroup_Server_Id", required = true, type = Integer.class, nillable = true)
    protected Integer serverGroupServerId;
    @XmlElement(name = "AddOrRemove_Code")
    protected String addOrRemoveCode;

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
     * Gets the value of the serverGroupServerId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getServerGroupServerId() {
        return serverGroupServerId;
    }

    /**
     * Sets the value of the serverGroupServerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setServerGroupServerId(Integer value) {
        this.serverGroupServerId = value;
    }

    /**
     * Gets the value of the addOrRemoveCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddOrRemoveCode() {
        return addOrRemoveCode;
    }

    /**
     * Sets the value of the addOrRemoveCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddOrRemoveCode(String value) {
        this.addOrRemoveCode = value;
    }

}
