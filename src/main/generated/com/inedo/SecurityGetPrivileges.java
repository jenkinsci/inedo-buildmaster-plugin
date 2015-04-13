
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
 *         &lt;element name="Directory_Provider_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PrincipalType_Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "directoryProviderId",
    "principalTypeCode"
})
@XmlRootElement(name = "Security_GetPrivileges")
public class SecurityGetPrivileges {

    protected String apiKey;
    @XmlElement(name = "Directory_Provider_Id", required = true, type = Integer.class, nillable = true)
    protected Integer directoryProviderId;
    @XmlElement(name = "PrincipalType_Code")
    protected String principalTypeCode;

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
     * Gets the value of the directoryProviderId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDirectoryProviderId() {
        return directoryProviderId;
    }

    /**
     * Sets the value of the directoryProviderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDirectoryProviderId(Integer value) {
        this.directoryProviderId = value;
    }

    /**
     * Gets the value of the principalTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrincipalTypeCode() {
        return principalTypeCode;
    }

    /**
     * Sets the value of the principalTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrincipalTypeCode(String value) {
        this.principalTypeCode = value;
    }

}
