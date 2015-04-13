
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
 *         &lt;element name="ReleaseExists_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NewerDeployedReleaseExists_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "releaseExistsIndicator",
    "newerDeployedReleaseExistsIndicator"
})
@XmlRootElement(name = "Releases_ReleaseExistsResponse")
public class ReleasesReleaseExistsResponse {

    @XmlElement(name = "ReleaseExists_Indicator")
    protected String releaseExistsIndicator;
    @XmlElement(name = "NewerDeployedReleaseExists_Indicator")
    protected String newerDeployedReleaseExistsIndicator;

    /**
     * Gets the value of the releaseExistsIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReleaseExistsIndicator() {
        return releaseExistsIndicator;
    }

    /**
     * Sets the value of the releaseExistsIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReleaseExistsIndicator(String value) {
        this.releaseExistsIndicator = value;
    }

    /**
     * Gets the value of the newerDeployedReleaseExistsIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewerDeployedReleaseExistsIndicator() {
        return newerDeployedReleaseExistsIndicator;
    }

    /**
     * Sets the value of the newerDeployedReleaseExistsIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewerDeployedReleaseExistsIndicator(String value) {
        this.newerDeployedReleaseExistsIndicator = value;
    }

}
