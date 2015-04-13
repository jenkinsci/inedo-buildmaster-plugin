
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
 *         &lt;element name="DeploymentPlanActionGroup_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Active_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ActionGroup_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ActionGroup_Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Predicate_Configuration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Server_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Server_Variable_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Deployable_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Deployable_Application_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Iterate_Servers_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Iterate_Deployables_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Parallel_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OnSuccess_DeploymentPlanActionGroup_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OnFailure_DeploymentPlanActionGroup_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OnFailure_Continue_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "deploymentPlanActionGroupId",
    "activeIndicator",
    "actionGroupName",
    "actionGroupDescription",
    "predicateConfiguration",
    "serverId",
    "serverVariableName",
    "deployableName",
    "deployableApplicationId",
    "iterateServersIndicator",
    "iterateDeployablesIndicator",
    "parallelIndicator",
    "onSuccessDeploymentPlanActionGroupId",
    "onFailureDeploymentPlanActionGroupId",
    "onFailureContinueIndicator"
})
@XmlRootElement(name = "Plans_UpdateDeploymentPlanActionGroup")
public class PlansUpdateDeploymentPlanActionGroup {

    protected String apiKey;
    @XmlElement(name = "DeploymentPlanActionGroup_Id", required = true, type = Integer.class, nillable = true)
    protected Integer deploymentPlanActionGroupId;
    @XmlElement(name = "Active_Indicator")
    protected String activeIndicator;
    @XmlElement(name = "ActionGroup_Name")
    protected String actionGroupName;
    @XmlElement(name = "ActionGroup_Description")
    protected String actionGroupDescription;
    @XmlElement(name = "Predicate_Configuration")
    protected String predicateConfiguration;
    @XmlElement(name = "Server_Id", required = true, type = Integer.class, nillable = true)
    protected Integer serverId;
    @XmlElement(name = "Server_Variable_Name")
    protected String serverVariableName;
    @XmlElement(name = "Deployable_Name")
    protected String deployableName;
    @XmlElement(name = "Deployable_Application_Id", required = true, type = Integer.class, nillable = true)
    protected Integer deployableApplicationId;
    @XmlElement(name = "Iterate_Servers_Indicator")
    protected String iterateServersIndicator;
    @XmlElement(name = "Iterate_Deployables_Indicator")
    protected String iterateDeployablesIndicator;
    @XmlElement(name = "Parallel_Indicator")
    protected String parallelIndicator;
    @XmlElement(name = "OnSuccess_DeploymentPlanActionGroup_Id", required = true, type = Integer.class, nillable = true)
    protected Integer onSuccessDeploymentPlanActionGroupId;
    @XmlElement(name = "OnFailure_DeploymentPlanActionGroup_Id", required = true, type = Integer.class, nillable = true)
    protected Integer onFailureDeploymentPlanActionGroupId;
    @XmlElement(name = "OnFailure_Continue_Indicator")
    protected String onFailureContinueIndicator;

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
     * Gets the value of the deploymentPlanActionGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDeploymentPlanActionGroupId() {
        return deploymentPlanActionGroupId;
    }

    /**
     * Sets the value of the deploymentPlanActionGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDeploymentPlanActionGroupId(Integer value) {
        this.deploymentPlanActionGroupId = value;
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
     * Gets the value of the actionGroupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionGroupName() {
        return actionGroupName;
    }

    /**
     * Sets the value of the actionGroupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionGroupName(String value) {
        this.actionGroupName = value;
    }

    /**
     * Gets the value of the actionGroupDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionGroupDescription() {
        return actionGroupDescription;
    }

    /**
     * Sets the value of the actionGroupDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionGroupDescription(String value) {
        this.actionGroupDescription = value;
    }

    /**
     * Gets the value of the predicateConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPredicateConfiguration() {
        return predicateConfiguration;
    }

    /**
     * Sets the value of the predicateConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPredicateConfiguration(String value) {
        this.predicateConfiguration = value;
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
     * Gets the value of the deployableName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeployableName() {
        return deployableName;
    }

    /**
     * Sets the value of the deployableName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeployableName(String value) {
        this.deployableName = value;
    }

    /**
     * Gets the value of the deployableApplicationId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDeployableApplicationId() {
        return deployableApplicationId;
    }

    /**
     * Sets the value of the deployableApplicationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDeployableApplicationId(Integer value) {
        this.deployableApplicationId = value;
    }

    /**
     * Gets the value of the iterateServersIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIterateServersIndicator() {
        return iterateServersIndicator;
    }

    /**
     * Sets the value of the iterateServersIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIterateServersIndicator(String value) {
        this.iterateServersIndicator = value;
    }

    /**
     * Gets the value of the iterateDeployablesIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIterateDeployablesIndicator() {
        return iterateDeployablesIndicator;
    }

    /**
     * Sets the value of the iterateDeployablesIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIterateDeployablesIndicator(String value) {
        this.iterateDeployablesIndicator = value;
    }

    /**
     * Gets the value of the parallelIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParallelIndicator() {
        return parallelIndicator;
    }

    /**
     * Sets the value of the parallelIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParallelIndicator(String value) {
        this.parallelIndicator = value;
    }

    /**
     * Gets the value of the onSuccessDeploymentPlanActionGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOnSuccessDeploymentPlanActionGroupId() {
        return onSuccessDeploymentPlanActionGroupId;
    }

    /**
     * Sets the value of the onSuccessDeploymentPlanActionGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOnSuccessDeploymentPlanActionGroupId(Integer value) {
        this.onSuccessDeploymentPlanActionGroupId = value;
    }

    /**
     * Gets the value of the onFailureDeploymentPlanActionGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOnFailureDeploymentPlanActionGroupId() {
        return onFailureDeploymentPlanActionGroupId;
    }

    /**
     * Sets the value of the onFailureDeploymentPlanActionGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOnFailureDeploymentPlanActionGroupId(Integer value) {
        this.onFailureDeploymentPlanActionGroupId = value;
    }

    /**
     * Gets the value of the onFailureContinueIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnFailureContinueIndicator() {
        return onFailureContinueIndicator;
    }

    /**
     * Sets the value of the onFailureContinueIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnFailureContinueIndicator(String value) {
        this.onFailureContinueIndicator = value;
    }

}
