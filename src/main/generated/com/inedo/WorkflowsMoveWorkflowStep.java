
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
 *         &lt;element name="Step_Sequence" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="New_Step_Sequence" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "stepSequence",
    "newStepSequence"
})
@XmlRootElement(name = "Workflows_MoveWorkflowStep")
public class WorkflowsMoveWorkflowStep {

    protected String apiKey;
    @XmlElement(name = "Workflow_Id", required = true, type = Integer.class, nillable = true)
    protected Integer workflowId;
    @XmlElement(name = "Step_Sequence", required = true, type = Integer.class, nillable = true)
    protected Integer stepSequence;
    @XmlElement(name = "New_Step_Sequence", required = true, type = Integer.class, nillable = true)
    protected Integer newStepSequence;

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
     * Gets the value of the stepSequence property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStepSequence() {
        return stepSequence;
    }

    /**
     * Sets the value of the stepSequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStepSequence(Integer value) {
        this.stepSequence = value;
    }

    /**
     * Gets the value of the newStepSequence property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNewStepSequence() {
        return newStepSequence;
    }

    /**
     * Sets the value of the newStepSequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNewStepSequence(Integer value) {
        this.newStepSequence = value;
    }

}
