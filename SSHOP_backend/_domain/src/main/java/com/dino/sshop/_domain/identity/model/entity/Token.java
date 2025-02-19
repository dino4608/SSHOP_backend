package com.dino.sshop._domain.identity.model.entity;

import com.dino.sshop._domain.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.Instant;

@Entity
@Table(name = "tokens")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE tokens SET deleted = true WHERE account_id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token extends BaseEntity {

    @Id
    String id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId", updatable = false, nullable = false)
    @JsonIgnore
    Account account;

    @Column(columnDefinition = "VARCHAR(1000)")
    String refreshToken;

    Instant refreshExpDate;
}
