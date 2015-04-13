
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
 *         &lt;element name="ProviderType_Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExecuteOn_Server_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Provider_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Provider_Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Provider_Configuration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Internal_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "providerTypeCode",
    "executeOnServerId",
    "providerName",
    "providerDescription",
    "providerConfiguration",
    "internalIndicator"
})
@XmlRootElement(name = "Providers_CreateOrUpdateProvider")
public class ProvidersCreateOrUpdateProvider {

    protected String apiKey;
    @XmlElement(name = "ProviderType_Code")
    protected String providerTypeCode;
    @XmlElement(name = "ExecuteOn_Server_Id", required = true, type = Integer.class, nillable = true)
    protected Integer executeOnServerId;
    @XmlElement(name = "Provider_Name")
    protected String providerName;
    @XmlElement(name = "Provider_Description")
    protected String providerDescription;
    @XmlElement(name = "Provider_Configuration")
    protected String providerConfiguration;
    @XmlElement(name = "Internal_Indicator")
    protected String internalIndicator;

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
     * Gets the value of the providerTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProviderTypeCode() {
        return providerTypeCode;
    }

    /**
     * Sets the value of the providerTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProviderTypeCode(String value) {
        this.providerTypeCode = value;
    }

    /**
     * Gets the value of the executeOnServerId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExecuteOnServerId() {
        return executeOnServerId;
    }

    /**
     * Sets the value of the executeOnServerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExecuteOnServerId(Integer value) {
        this.executeOnServerId = value;
    }

    /**
     * Gets the value of the providerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProviderName() {
        return providerName;
    }

    /**
     * Sets the value of the providerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProviderName(String value) {
        this.providerName = value;
    }

    /**
     * Gets the value of the providerDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProviderDescription() {
        return providerDescription;
    }

    /**
     * Sets the value of the providerDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProviderDescription(String value) {
        this.providerDescription = value;
    }

    /**
     * Gets the value of the providerConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProviderConfiguration() {
        return providerConfiguration;
    }

    /**
     * Sets the value of the providerConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProviderConfiguration(String value) {
        this.providerConfiguration = value;
    }

    /**
     * Gets the value of the internalIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternalIndicator() {
        return internalIndicator;
    }

    /**
     * Sets the value of the internalIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternalIndicator(String value) {
        this.internalIndicator = value;
    }

}
