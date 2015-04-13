
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
 *         &lt;element name="Active_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConfigurationFile_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FilePath_Text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description_Text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Deployable_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "activeIndicator",
    "configurationFileName",
    "filePathText",
    "descriptionText",
    "deployableId"
})
@XmlRootElement(name = "ConfigurationFiles_UpdateConfigurationFile")
public class ConfigurationFilesUpdateConfigurationFile {

    protected String apiKey;
    @XmlElement(name = "ConfigurationFile_Id", required = true, type = Integer.class, nillable = true)
    protected Integer configurationFileId;
    @XmlElement(name = "Active_Indicator")
    protected String activeIndicator;
    @XmlElement(name = "ConfigurationFile_Name")
    protected String configurationFileName;
    @XmlElement(name = "FilePath_Text")
    protected String filePathText;
    @XmlElement(name = "Description_Text")
    protected String descriptionText;
    @XmlElement(name = "Deployable_Id", required = true, type = Integer.class, nillable = true)
    protected Integer deployableId;

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
     * Gets the value of the configurationFileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfigurationFileName() {
        return configurationFileName;
    }

    /**
     * Sets the value of the configurationFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfigurationFileName(String value) {
        this.configurationFileName = value;
    }

    /**
     * Gets the value of the filePathText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilePathText() {
        return filePathText;
    }

    /**
     * Sets the value of the filePathText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilePathText(String value) {
        this.filePathText = value;
    }

    /**
     * Gets the value of the descriptionText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescriptionText() {
        return descriptionText;
    }

    /**
     * Sets the value of the descriptionText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescriptionText(String value) {
        this.descriptionText = value;
    }

    /**
     * Gets the value of the deployableId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDeployableId() {
        return deployableId;
    }

    /**
     * Sets the value of the deployableId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDeployableId(Integer value) {
        this.deployableId = value;
    }

}
