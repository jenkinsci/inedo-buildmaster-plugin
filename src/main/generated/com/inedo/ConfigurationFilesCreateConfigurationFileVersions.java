
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
 *         &lt;element name="ConfigurationFiles_Xml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReleaseNumbers_Csv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "configurationFilesXml",
    "releaseNumbersCsv"
})
@XmlRootElement(name = "ConfigurationFiles_CreateConfigurationFileVersions")
public class ConfigurationFilesCreateConfigurationFileVersions {

    protected String apiKey;
    @XmlElement(name = "ConfigurationFile_Id", required = true, type = Integer.class, nillable = true)
    protected Integer configurationFileId;
    @XmlElement(name = "ConfigurationFiles_Xml")
    protected String configurationFilesXml;
    @XmlElement(name = "ReleaseNumbers_Csv")
    protected String releaseNumbersCsv;

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
     * Gets the value of the configurationFilesXml property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfigurationFilesXml() {
        return configurationFilesXml;
    }

    /**
     * Sets the value of the configurationFilesXml property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfigurationFilesXml(String value) {
        this.configurationFilesXml = value;
    }

    /**
     * Gets the value of the releaseNumbersCsv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReleaseNumbersCsv() {
        return releaseNumbersCsv;
    }

    /**
     * Sets the value of the releaseNumbersCsv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReleaseNumbersCsv(String value) {
        this.releaseNumbersCsv = value;
    }

}
