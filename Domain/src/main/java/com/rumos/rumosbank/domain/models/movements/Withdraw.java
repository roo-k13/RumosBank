package com.rumos.rumosbank.domain.models.movements;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "withdraws")
public class Withdraw extends Operation { }
