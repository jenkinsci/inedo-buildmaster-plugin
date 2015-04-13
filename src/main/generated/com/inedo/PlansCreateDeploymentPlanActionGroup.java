
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
 *         &lt;element name="DeploymentPlan_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Deployable_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Deployable_Application_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DeploymentPlanActionGroup_Sequence" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Server_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Server_Variable_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Active_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ActionGroup_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ActionGroup_Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Predicate_Configuration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeploymentPlanActionGroup_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "deploymentPlanId",
    "deployableName",
    "deployableApplicationId",
    "deploymentPlanActionGroupSequence",
    "serverId",
    "serverVariableName",
    "activeIndicator",
    "actionGroupName",
    "actionGroupDescription",
    "predicateConfiguration",
    "deploymentPlanActionGroupId"
})
@XmlRootElement(name = "Plans_CreateDeploymentPlanActionGroup")
public class PlansCreateDeploymentPlanActionGroup {

    protected String apiKey;
    @XmlElement(name = "DeploymentPlan_Id", required = true, type = Integer.class, nillable = true)
    protected Integer deploymentPlanId;
    @XmlElement(name = "Deployable_Name")
    protected String deployableName;
    @XmlElement(name = "Deployable_Application_Id", required = true, type = Integer.class, nillable = true)
    protected Integer deployableApplicationId;
    @XmlElement(name = "DeploymentPlanActionGroup_Sequence", required = true, type = Integer.class, nillable = true)
    protected Integer deploymentPlanActionGroupSequence;
    @XmlElement(name = "Server_Id", required = true, type = Integer.class, nillable = true)
    protected Integer serverId;
    @XmlElement(name = "Server_Variable_Name")
    protected String serverVariableName;
    @XmlElement(name = "Active_Indicator")
    protected String activeIndicator;
    @XmlElement(name = "ActionGroup_Name")
    protected String actionGroupName;
    @XmlElement(name = "ActionGroup_Description")
    protected String actionGroupDescription;
    @XmlElement(name = "Predicate_Configuration")
    protected String predicateConfiguration;
    @XmlElement(name = "DeploymentPlanActionGroup_Id", required = true, type = Integer.class, nillable = true)
    protected Integer deploymentPlanActionGroupId;

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
     * Gets the value of the deploymentPlanId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDeploymentPlanId() {
        return deploymentPlanId;
    }

    /**
     * Sets the value of the deploymentPlanId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDeploymentPlanId(Integer value) {
        this.deploymentPlanId = value;
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
     * Gets the value of the deploymentPlanActionGroupSequence property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDeploymentPlanActionGroupSequence() {
        return deploymentPlanActionGroupSequence;
    }

    /**
     * Sets the value of the deploymentPlanActionGroupSequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDeploymentPlanActionGroupSequence(Integer value) {
        this.deploymentPlanActionGroupSequence = value;
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

}
