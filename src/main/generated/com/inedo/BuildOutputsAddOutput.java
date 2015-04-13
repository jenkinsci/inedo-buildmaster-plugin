
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
 *         &lt;element name="BuildExecution_PlanAction_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Output_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Output_Bytes" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="OutputType_Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "buildExecutionPlanActionId",
    "outputName",
    "outputBytes",
    "outputTypeCode"
})
@XmlRootElement(name = "BuildOutputs_AddOutput")
public class BuildOutputsAddOutput {

    protected String apiKey;
    @XmlElement(name = "BuildExecution_PlanAction_Id", required = true, type = Integer.class, nillable = true)
    protected Integer buildExecutionPlanActionId;
    @XmlElement(name = "Output_Name")
    protected String outputName;
    @XmlElement(name = "Output_Bytes")
    protected byte[] outputBytes;
    @XmlElement(name = "OutputType_Code")
    protected String outputTypeCode;

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
     * Gets the value of the buildExecutionPlanActionId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBuildExecutionPlanActionId() {
        return buildExecutionPlanActionId;
    }

    /**
     * Sets the value of the buildExecutionPlanActionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBuildExecutionPlanActionId(Integer value) {
        this.buildExecutionPlanActionId = value;
    }

    /**
     * Gets the value of the outputName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputName() {
        return outputName;
    }

    /**
     * Sets the value of the outputName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputName(String value) {
        this.outputName = value;
    }

    /**
     * Gets the value of the outputBytes property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getOutputBytes() {
        return outputBytes;
    }

    /**
     * Sets the value of the outputBytes property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setOutputBytes(byte[] value) {
        this.outputBytes = value;
    }

    /**
     * Gets the value of the outputTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputTypeCode() {
        return outputTypeCode;
    }

    /**
     * Sets the value of the outputTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputTypeCode(String value) {
        this.outputTypeCode = value;
    }

}
