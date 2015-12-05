package ua.alex.source.webtester.entities;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.MappedSuperclass;
import java.beans.Transient;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntity implements IEntity, Comparable<AbstractEntity> {
    private static final long serialVersionUID = 9161447837905426010L;

    @Transient
    public abstract Serializable getId();

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;

        if (!(obj instanceof AbstractEntity)) return false;
        AbstractEntity o = (AbstractEntity) obj;

        if (getId() == null) {
            if (o.getId() != null) {
                return false;
            }
        } else if (!getId().equals(o.getId())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

/*    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }*/

    @Override
    public int compareTo(AbstractEntity o) {
        Object id1 = getId();
        Object id2 = o != null ? o.getId() : null;

        return new CompareToBuilder().append(id1,id2).toComparison();
    }

}
