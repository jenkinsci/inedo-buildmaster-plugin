
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
 *         &lt;element name="ReleaseNote_Sequence" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "releaseNoteSequence"
})
@XmlRootElement(name = "Releases_CreateOrUpdateReleaseNoteResponse")
public class ReleasesCreateOrUpdateReleaseNoteResponse {

    @XmlElement(name = "ReleaseNote_Sequence", required = true, type = Integer.class, nillable = true)
    protected Integer releaseNoteSequence;

    /**
     * Gets the value of the releaseNoteSequence property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getReleaseNoteSequence() {
        return releaseNoteSequence;
    }

    /**
     * Sets the value of the releaseNoteSequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setReleaseNoteSequence(Integer value) {
        this.releaseNoteSequence = value;
    }

}
