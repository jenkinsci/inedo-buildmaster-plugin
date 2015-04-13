
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
 *         &lt;element name="ConfigurationFile_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IncludeInactiveInstances_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "configurationFileId",
    "includeInactiveInstancesIndicator"
})
@XmlRootElement(name = "ConfigurationFiles_GetConfigurationFile")
public class ConfigurationFilesGetConfigurationFile {

    protected String apiKey;
    @XmlElement(name = "ConfigurationFile_Id", required = true, type = Integer.class, nillable = true)
    protected Integer configurationFileId;
    @XmlElement(name = "IncludeInactiveInstances_Indicator")
    protected String includeInactiveInstancesIndicator;

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
     * Gets the value of the configurationFileId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getConfigurationFileId() {
        return configurationFileId;
    }

    /**
     * Sets the value of the configurationFileId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setConfigurationFileId(Integer value) {
        this.configurationFileId = value;
    }

    /**
     * Gets the value of the includeInactiveInstancesIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncludeInactiveInstancesIndicator() {
        return includeInactiveInstancesIndicator;
    }

    /**
     * Sets the value of the includeInactiveInstancesIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncludeInactiveInstancesIndicator(String value) {
        this.includeInactiveInstancesIndicator = value;
    }

}
