
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
 *         &lt;element name="IncludeInactive_Environments_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IncludeInactive_Servers_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "includeInactiveEnvironmentsIndicator",
    "includeInactiveServersIndicator"
})
@XmlRootElement(name = "Environments_GetEnvironmentsAndServers")
public class EnvironmentsGetEnvironmentsAndServers {

    protected String apiKey;
    @XmlElement(name = "IncludeInactive_Environments_Indicator")
    protected String includeInactiveEnvironmentsIndicator;
    @XmlElement(name = "IncludeInactive_Servers_Indicator")
    protected String includeInactiveServersIndicator;

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
     * Gets the value of the includeInactiveEnvironmentsIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncludeInactiveEnvironmentsIndicator() {
        return includeInactiveEnvironmentsIndicator;
    }

    /**
     * Sets the value of the includeInactiveEnvironmentsIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncludeInactiveEnvironmentsIndicator(String value) {
        this.includeInactiveEnvironmentsIndicator = value;
    }

    /**
     * Gets the value of the includeInactiveServersIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncludeInactiveServersIndicator() {
        return includeInactiveServersIndicator;
    }

    /**
     * Sets the value of the includeInactiveServersIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncludeInactiveServersIndicator(String value) {
        this.includeInactiveServersIndicator = value;
    }

}
