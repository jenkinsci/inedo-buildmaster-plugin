
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
 *         &lt;element name="New_DeploymentPlanActionGroup_Sequence" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "newDeploymentPlanActionGroupSequence"
})
@XmlRootElement(name = "Plans_ResequenceActionGroup")
public class PlansResequenceActionGroup {

    protected String apiKey;
    @XmlElement(name = "DeploymentPlanActionGroup_Id", required = true, type = Integer.class, nillable = true)
    protected Integer deploymentPlanActionGroupId;
    @XmlElement(name = "New_DeploymentPlanActionGroup_Sequence", required = true, type = Integer.class, nillable = true)
    protected Integer newDeploymentPlanActionGroupSequence;

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
     * Gets the value of the newDeploymentPlanActionGroupSequence property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNewDeploymentPlanActionGroupSequence() {
        return newDeploymentPlanActionGroupSequence;
    }

    /**
     * Sets the value of the newDeploymentPlanActionGroupSequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNewDeploymentPlanActionGroupSequence(Integer value) {
        this.newDeploymentPlanActionGroupSequence = value;
    }

}
