
package com.inedo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2001.xmlschema.Schema;


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
 *         &lt;element name="Workflows_GetWorkflowResult" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.w3.org/2001/XMLSchema}schema"/>
 *                   &lt;any/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "workflowsGetWorkflowResult"
})
@XmlRootElement(name = "Workflows_GetWorkflowResponse")
public class WorkflowsGetWorkflowResponse {

    @XmlElement(name = "Workflows_GetWorkflowResult")
    protected WorkflowsGetWorkflowResponse.WorkflowsGetWorkflowResult workflowsGetWorkflowResult;

    /**
     * Gets the value of the workflowsGetWorkflowResult property.
     * 
     * @return
     *     possible object is
     *     {@link WorkflowsGetWorkflowResponse.WorkflowsGetWorkflowResult }
     *     
     */
    public WorkflowsGetWorkflowResponse.WorkflowsGetWorkflowResult getWorkflowsGetWorkflowResult() {
        return workflowsGetWorkflowResult;
    }

    /**
     * Sets the value of the workflowsGetWorkflowResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkflowsGetWorkflowResponse.WorkflowsGetWorkflowResult }
     *     
     */
    public void setWorkflowsGetWorkflowResult(WorkflowsGetWorkflowResponse.WorkflowsGetWorkflowResult value) {
        this.workflowsGetWorkflowResult = value;
    }


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
     *         &lt;element ref="{http://www.w3.org/2001/XMLSchema}schema"/>
     *         &lt;any/>
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
        "schema",
        "any"
    })
    public static class WorkflowsGetWorkflowResult {

        @XmlElement(namespace = "http://www.w3.org/2001/XMLSchema", required = true)
        protected Schema schema;
        @XmlAnyElement(lax = true)
        protected Object any;

        /**
         * Gets the value of the schema property.
         * 
         * @return
         *     possible object is
         *     {@link Schema }
         *     
         */
        public Schema getSchema() {
            return schema;
        }

        /**
         * Sets the value of the schema property.
         * 
         * @param value
         *     allowed object is
         *     {@link Schema }
         *     
         */
        public void setSchema(Schema value) {
            this.schema = value;
        }

        /**
         * Gets the value of the any property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getAny() {
            return any;
        }

        /**
         * Sets the value of the any property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setAny(Object value) {
            this.any = value;
        }

    }

}
