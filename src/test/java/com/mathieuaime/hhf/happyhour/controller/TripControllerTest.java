package com.mathieuaime.hhf.happyhour.controller;

import com.mathieuaime.hhf.happyhour.service.TripService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(TripService.class)
@WebFluxTest(TripController.class)
class TripControllerTest {

}