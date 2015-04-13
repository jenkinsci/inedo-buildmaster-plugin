
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
 *         &lt;element name="EventListener_Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EventListener_Configuration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Owner_User_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Active_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WorkflowStep_Workflow_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="WorkflowStep_Environment_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="BuildStep_Workflow_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EventCodeList_Csv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ApplicationIdList_Csv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ApplicationGroupIdList_Csv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnvironmentIdList_Csv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EventListener_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "eventListenerDescription",
    "eventListenerConfiguration",
    "ownerUserName",
    "activeIndicator",
    "workflowStepWorkflowId",
    "workflowStepEnvironmentId",
    "buildStepWorkflowId",
    "eventCodeListCsv",
    "applicationIdListCsv",
    "applicationGroupIdListCsv",
    "environmentIdListCsv",
    "eventListenerId"
})
@XmlRootElement(name = "EventListeners_CreateOrUpdateEventListeners")
public class EventListenersCreateOrUpdateEventListeners {

    protected String apiKey;
    @XmlElement(name = "EventListener_Description")
    protected String eventListenerDescription;
    @XmlElement(name = "EventListener_Configuration")
    protected String eventListenerConfiguration;
    @XmlElement(name = "Owner_User_Name")
    protected String ownerUserName;
    @XmlElement(name = "Active_Indicator")
    protected String activeIndicator;
    @XmlElement(name = "WorkflowStep_Workflow_Id", required = true, type = Integer.class, nillable = true)
    protected Integer workflowStepWorkflowId;
    @XmlElement(name = "WorkflowStep_Environment_Id", required = true, type = Integer.class, nillable = true)
    protected Integer workflowStepEnvironmentId;
    @XmlElement(name = "BuildStep_Workflow_Id", required = true, type = Integer.class, nillable = true)
    protected Integer buildStepWorkflowId;
    @XmlElement(name = "EventCodeList_Csv")
    protected String eventCodeListCsv;
    @XmlElement(name = "ApplicationIdList_Csv")
    protected String applicationIdListCsv;
    @XmlElement(name = "ApplicationGroupIdList_Csv")
    protected String applicationGroupIdListCsv;
    @XmlElement(name = "EnvironmentIdList_Csv")
    protected String environmentIdListCsv;
    @XmlElement(name = "EventListener_Id", required = true, type = Integer.class, nillable = true)
    protected Integer eventListenerId;

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
     * Gets the value of the eventListenerDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventListenerDescription() {
        return eventListenerDescription;
    }

    /**
     * Sets the value of the eventListenerDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventListenerDescription(String value) {
        this.eventListenerDescription = value;
    }

    /**
     * Gets the value of the eventListenerConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventListenerConfiguration() {
        return eventListenerConfiguration;
    }

    /**
     * Sets the value of the eventListenerConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventListenerConfiguration(String value) {
        this.eventListenerConfiguration = value;
    }

    /**
     * Gets the value of the ownerUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerUserName() {
        return ownerUserName;
    }

    /**
     * Sets the value of the ownerUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerUserName(String value) {
        this.ownerUserName = value;
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
     * Gets the value of the workflowStepWorkflowId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWorkflowStepWorkflowId() {
        return workflowStepWorkflowId;
    }

    /**
     * Sets the value of the workflowStepWorkflowId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWorkflowStepWorkflowId(Integer value) {
        this.workflowStepWorkflowId = value;
    }

    /**
     * Gets the value of the workflowStepEnvironmentId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWorkflowStepEnvironmentId() {
        return workflowStepEnvironmentId;
    }

    /**
     * Sets the value of the workflowStepEnvironmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWorkflowStepEnvironmentId(Integer value) {
        this.workflowStepEnvironmentId = value;
    }

    /**
     * Gets the value of the buildStepWorkflowId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBuildStepWorkflowId() {
        return buildStepWorkflowId;
    }

    /**
     * Sets the value of the buildStepWorkflowId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBuildStepWorkflowId(Integer value) {
        this.buildStepWorkflowId = value;
    }

    /**
     * Gets the value of the eventCodeListCsv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventCodeListCsv() {
        return eventCodeListCsv;
    }

    /**
     * Sets the value of the eventCodeListCsv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventCodeListCsv(String value) {
        this.eventCodeListCsv = value;
    }

    /**
     * Gets the value of the applicationIdListCsv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationIdListCsv() {
        return applicationIdListCsv;
    }

    /**
     * Sets the value of the applicationIdListCsv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationIdListCsv(String value) {
        this.applicationIdListCsv = value;
    }

    /**
     * Gets the value of the applicationGroupIdListCsv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationGroupIdListCsv() {
        return applicationGroupIdListCsv;
    }

    /**
     * Sets the value of the applicationGroupIdListCsv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationGroupIdListCsv(String value) {
        this.applicationGroupIdListCsv = value;
    }

    /**
     * Gets the value of the environmentIdListCsv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnvironmentIdListCsv() {
        return environmentIdListCsv;
    }

    /**
     * Sets the value of the environmentIdListCsv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnvironmentIdListCsv(String value) {
        this.environmentIdListCsv = value;
    }

    /**
     * Gets the value of the eventListenerId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEventListenerId() {
        return eventListenerId;
    }

    /**
     * Sets the value of the eventListenerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEventListenerId(Integer value) {
        this.eventListenerId = value;
    }

}
