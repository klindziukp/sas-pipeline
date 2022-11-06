package com.klindziuk.sas.pipeline.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataR2dbcTest
@ExtendWith(SpringExtension.class)
public abstract class BaseJpaRepositoryTest {

  @Autowired
  PlayerRepository playerRepository;

}
