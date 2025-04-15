package com.godeltech.bikesharing.persistence.speicification;

import com.godeltech.bikesharing.persistence.entity.ClientAccount;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Or({
        @Spec(path = SpecConstant.FieldName.NAME, params = SpecConstant.FieldName.LOOKUP, spec = LikeIgnoreCase.class),
        @Spec(path = SpecConstant.FieldName.PHONE_NUMBER, params = SpecConstant.FieldName.LOOKUP, spec = Like.class),
})
public interface ClientAccountSearchCriteria extends Specification<ClientAccount> {
}
