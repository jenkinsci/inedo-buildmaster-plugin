
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
 *         &lt;element name="Server_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Server_Variable_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Long_Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResumeNextOnFailure_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Action_Configuration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Short_Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Active_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Retry_Count" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LogFailureAsWarning_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Target_Server_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Target_Server_Variable_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExtensionConfiguration_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "serverId",
    "serverVariableName",
    "longDescription",
    "resumeNextOnFailureIndicator",
    "actionConfiguration",
    "shortDescription",
    "activeIndicator",
    "retryCount",
    "logFailureAsWarningIndicator",
    "targetServerId",
    "targetServerVariableName",
    "extensionConfigurationId"
})
@XmlRootElement(name = "Plans_CreateOrUpdateAction")
public class PlansCreateOrUpdateAction {

    protected String apiKey;
    @XmlElement(name = "ActionGroup_Id", required = true, type = Integer.class, nillable = true)
    protected Integer actionGroupId;
    @XmlElement(name = "Server_Id", required = true, type = Integer.class, nillable = true)
    protected Integer serverId;
    @XmlElement(name = "Server_Variable_Name")
    protected String serverVariableName;
    @XmlElement(name = "Long_Description")
    protected String longDescription;
    @XmlElement(name = "ResumeNextOnFailure_Indicator")
    protected String resumeNextOnFailureIndicator;
    @XmlElement(name = "Action_Configuration")
    protected String actionConfiguration;
    @XmlElement(name = "Short_Description")
    protected String shortDescription;
    @XmlElement(name = "Active_Indicator")
    protected String activeIndicator;
    @XmlElement(name = "Retry_Count", required = true, type = Integer.class, nillable = true)
    protected Integer retryCount;
    @XmlElement(name = "LogFailureAsWarning_Indicator")
    protected String logFailureAsWarningIndicator;
    @XmlElement(name = "Target_Server_Id", required = true, type = Integer.class, nillable = true)
    protected Integer targetServerId;
    @XmlElement(name = "Target_Server_Variable_Name")
    protected String targetServerVariableName;
    @XmlElement(name = "ExtensionConfiguration_Id", required = true, type = Integer.class, nillable = true)
    protected Integer extensionConfigurationId;

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

    /**
     * Gets the value of the longDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * Sets the value of the longDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongDescription(String value) {
        this.longDescription = value;
    }

    /**
     * Gets the value of the resumeNextOnFailureIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResumeNextOnFailureIndicator() {
        return resumeNextOnFailureIndicator;
    }

    /**
     * Sets the value of the resumeNextOnFailureIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResumeNextOnFailureIndicator(String value) {
        this.resumeNextOnFailureIndicator = value;
    }

    /**
     * Gets the value of the actionConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionConfiguration() {
        return actionConfiguration;
    }

    /**
     * Sets the value of the actionConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionConfiguration(String value) {
        this.actionConfiguration = value;
    }

    /**
     * Gets the value of the shortDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Sets the value of the shortDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortDescription(String value) {
        this.shortDescription = value;
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
     * Gets the value of the retryCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRetryCount() {
        return retryCount;
    }

    /**
     * Sets the value of the retryCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRetryCount(Integer value) {
        this.retryCount = value;
    }

    /**
     * Gets the value of the logFailureAsWarningIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogFailureAsWarningIndicator() {
        return logFailureAsWarningIndicator;
    }

    /**
     * Sets the value of the logFailureAsWarningIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogFailureAsWarningIndicator(String value) {
        this.logFailureAsWarningIndicator = value;
    }

    /**
     * Gets the value of the targetServerId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTargetServerId() {
        return targetServerId;
    }

    /**
     * Sets the value of the targetServerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTargetServerId(Integer value) {
        this.targetServerId = value;
    }

    /**
     * Gets the value of the targetServerVariableName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetServerVariableName() {
        return targetServerVariableName;
    }

    /**
     * Sets the value of the targetServerVariableName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetServerVariableName(String value) {
        this.targetServerVariableName = value;
    }

    /**
     * Gets the value of the extensionConfigurationId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExtensionConfigurationId() {
        return extensionConfigurationId;
    }

    /**
     * Sets the value of the extensionConfigurationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExtensionConfigurationId(Integer value) {
        this.extensionConfigurationId = value;
    }

}
