
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
 *         &lt;element name="Instance_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Environment_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Template_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Template_Instance_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransformType_Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "instanceName",
    "environmentId",
    "templateIndicator",
    "templateInstanceName",
    "transformTypeCode"
})
@XmlRootElement(name = "ConfigurationFiles_CreateConfigurationFileInstance")
public class ConfigurationFilesCreateConfigurationFileInstance {

    protected String apiKey;
    @XmlElement(name = "ConfigurationFile_Id", required = true, type = Integer.class, nillable = true)
    protected Integer configurationFileId;
    @XmlElement(name = "Instance_Name")
    protected String instanceName;
    @XmlElement(name = "Environment_Id", required = true, type = Integer.class, nillable = true)
    protected Integer environmentId;
    @XmlElement(name = "Template_Indicator")
    protected String templateIndicator;
    @XmlElement(name = "Template_Instance_Name")
    protected String templateInstanceName;
    @XmlElement(name = "TransformType_Code")
    protected String transformTypeCode;

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
     * Gets the value of the instanceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanceName() {
        return instanceName;
    }

    /**
     * Sets the value of the instanceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanceName(String value) {
        this.instanceName = value;
    }

    /**
     * Gets the value of the environmentId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEnvironmentId() {
        return environmentId;
    }

    /**
     * Sets the value of the environmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEnvironmentId(Integer value) {
        this.environmentId = value;
    }

    /**
     * Gets the value of the templateIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplateIndicator() {
        return templateIndicator;
    }

    /**
     * Sets the value of the templateIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplateIndicator(String value) {
        this.templateIndicator = value;
    }

    /**
     * Gets the value of the templateInstanceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplateInstanceName() {
        return templateInstanceName;
    }

    /**
     * Sets the value of the templateInstanceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplateInstanceName(String value) {
        this.templateInstanceName = value;
    }

    /**
     * Gets the value of the transformTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransformTypeCode() {
        return transformTypeCode;
    }

    /**
     * Sets the value of the transformTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransformTypeCode(String value) {
        this.transformTypeCode = value;
    }

}
