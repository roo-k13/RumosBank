package com.rumos.rumosbank.domain.models.movements;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "deposits")
public class Deposit extends Operation { }