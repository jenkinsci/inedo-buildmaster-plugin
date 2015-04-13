
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
 *         &lt;element name="Deployable_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DependsOn_Deployables_Csv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "deployableId",
    "dependsOnDeployablesCsv"
})
@XmlRootElement(name = "Applications_SetDeployableDependencies")
public class ApplicationsSetDeployableDependencies {

    protected String apiKey;
    @XmlElement(name = "Deployable_Id", required = true, type = Integer.class, nillable = true)
    protected Integer deployableId;
    @XmlElement(name = "DependsOn_Deployables_Csv")
    protected String dependsOnDeployablesCsv;

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
     * Gets the value of the deployableId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDeployableId() {
        return deployableId;
    }

    /**
     * Sets the value of the deployableId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDeployableId(Integer value) {
        this.deployableId = value;
    }

    /**
     * Gets the value of the dependsOnDeployablesCsv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDependsOnDeployablesCsv() {
        return dependsOnDeployablesCsv;
    }

    /**
     * Sets the value of the dependsOnDeployablesCsv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDependsOnDeployablesCsv(String value) {
        this.dependsOnDeployablesCsv = value;
    }

}
