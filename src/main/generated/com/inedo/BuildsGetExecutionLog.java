
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
 *         &lt;element name="Execution_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="BuildExecution_DeploymentPlan_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Original_DeploymentPlanActionGroup_Sequence" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="BuildExecution_ActionGroupAction_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "executionId",
    "buildExecutionDeploymentPlanId",
    "originalDeploymentPlanActionGroupSequence",
    "buildExecutionActionGroupActionId"
})
@XmlRootElement(name = "Builds_GetExecutionLog")
public class BuildsGetExecutionLog {

    protected String apiKey;
    @XmlElement(name = "Execution_Id", required = true, type = Integer.class, nillable = true)
    protected Integer executionId;
    @XmlElement(name = "BuildExecution_DeploymentPlan_Id", required = true, type = Integer.class, nillable = true)
    protected Integer buildExecutionDeploymentPlanId;
    @XmlElement(name = "Original_DeploymentPlanActionGroup_Sequence", required = true, type = Integer.class, nillable = true)
    protected Integer originalDeploymentPlanActionGroupSequence;
    @XmlElement(name = "BuildExecution_ActionGroupAction_Id", required = true, type = Integer.class, nillable = true)
    protected Integer buildExecutionActionGroupActionId;

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
     * Gets the value of the executionId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExecutionId() {
        return executionId;
    }

    /**
     * Sets the value of the executionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExecutionId(Integer value) {
        this.executionId = value;
    }

    /**
     * Gets the value of the buildExecutionDeploymentPlanId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBuildExecutionDeploymentPlanId() {
        return buildExecutionDeploymentPlanId;
    }

    /**
     * Sets the value of the buildExecutionDeploymentPlanId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBuildExecutionDeploymentPlanId(Integer value) {
        this.buildExecutionDeploymentPlanId = value;
    }

    /**
     * Gets the value of the originalDeploymentPlanActionGroupSequence property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOriginalDeploymentPlanActionGroupSequence() {
        return originalDeploymentPlanActionGroupSequence;
    }

    /**
     * Sets the value of the originalDeploymentPlanActionGroupSequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOriginalDeploymentPlanActionGroupSequence(Integer value) {
        this.originalDeploymentPlanActionGroupSequence = value;
    }

    /**
     * Gets the value of the buildExecutionActionGroupActionId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBuildExecutionActionGroupActionId() {
        return buildExecutionActionGroupActionId;
    }

    /**
     * Sets the value of the buildExecutionActionGroupActionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBuildExecutionActionGroupActionId(Integer value) {
        this.buildExecutionActionGroupActionId = value;
    }

}
