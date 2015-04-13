
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
 *         &lt;element name="Workflow_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Build_DeploymentPlan_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="BuildImporterTemplate_Configuration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "workflowId",
    "buildDeploymentPlanId",
    "buildImporterTemplateConfiguration"
})
@XmlRootElement(name = "Workflows_SetBuildStep")
public class WorkflowsSetBuildStep {

    protected String apiKey;
    @XmlElement(name = "Workflow_Id", required = true, type = Integer.class, nillable = true)
    protected Integer workflowId;
    @XmlElement(name = "Build_DeploymentPlan_Id", required = true, type = Integer.class, nillable = true)
    protected Integer buildDeploymentPlanId;
    @XmlElement(name = "BuildImporterTemplate_Configuration")
    protected String buildImporterTemplateConfiguration;

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
     * Gets the value of the workflowId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWorkflowId() {
        return workflowId;
    }

    /**
     * Sets the value of the workflowId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWorkflowId(Integer value) {
        this.workflowId = value;
    }

    /**
     * Gets the value of the buildDeploymentPlanId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBuildDeploymentPlanId() {
        return buildDeploymentPlanId;
    }

    /**
     * Sets the value of the buildDeploymentPlanId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBuildDeploymentPlanId(Integer value) {
        this.buildDeploymentPlanId = value;
    }

    /**
     * Gets the value of the buildImporterTemplateConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildImporterTemplateConfiguration() {
        return buildImporterTemplateConfiguration;
    }

    /**
     * Sets the value of the buildImporterTemplateConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildImporterTemplateConfiguration(String value) {
        this.buildImporterTemplateConfiguration = value;
    }

}
