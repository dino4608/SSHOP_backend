package com.dino.sshop._infrastructure.persistence.repository.shopping;

import com.dino.sshop._domain.shopping.model.entity.Address;
import com.dino.sshop._domain.shopping.repository.IAddressDomainRepository;
import com.dino.sshop._infrastructure.persistence.jpa.shopping.IAddressJpaMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AddressInfraRepository implements IAddressDomainRepository {

    IAddressJpaMapper addressJpaMapper;
    @Override
    public Optional<Address> findById(String id) {
        return this.addressJpaMapper.findById(id);
    }
}
