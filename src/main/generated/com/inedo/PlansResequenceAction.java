
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
 *         &lt;element name="Action_Sequence" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="New_Action_Sequence" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "actionSequence",
    "newActionSequence"
})
@XmlRootElement(name = "Plans_ResequenceAction")
public class PlansResequenceAction {

    protected String apiKey;
    @XmlElement(name = "DeploymentPlanActionGroup_Id", required = true, type = Integer.class, nillable = true)
    protected Integer deploymentPlanActionGroupId;
    @XmlElement(name = "Action_Sequence", required = true, type = Integer.class, nillable = true)
    protected Integer actionSequence;
    @XmlElement(name = "New_Action_Sequence", required = true, type = Integer.class, nillable = true)
    protected Integer newActionSequence;

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
     * Gets the value of the actionSequence property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getActionSequence() {
        return actionSequence;
    }

    /**
     * Sets the value of the actionSequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setActionSequence(Integer value) {
        this.actionSequence = value;
    }

    /**
     * Gets the value of the newActionSequence property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNewActionSequence() {
        return newActionSequence;
    }

    /**
     * Sets the value of the newActionSequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNewActionSequence(Integer value) {
        this.newActionSequence = value;
    }

}
