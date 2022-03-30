package com.springBajo8.springBajo8.web;


import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.service.IcitasReactivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController
public class citasReactivaResource {

    @Autowired
    private IcitasReactivaService icitasReactivaService;

    @PostMapping("/citasReactivas")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<citasDTOReactiva> save(@RequestBody citasDTOReactiva citasDTOReactiva) {
        return this.icitasReactivaService.save(citasDTOReactiva);
    }

    @DeleteMapping("/citasReactivas/{id}")
    private Mono<ResponseEntity<citasDTOReactiva>> delete(@PathVariable("id") String id) {
        return this.icitasReactivaService.delete(id)
                .flatMap(citasDTOReactiva -> Mono.just(ResponseEntity.ok(citasDTOReactiva)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @PutMapping("/citasReactivas/{id}")
    private Mono<ResponseEntity<citasDTOReactiva>> update(@PathVariable("id") String id, @RequestBody citasDTOReactiva citasDTOReactiva) {
        return this.icitasReactivaService.update(id, citasDTOReactiva)
                .flatMap(citasDTOReactiva1 -> Mono.just(ResponseEntity.ok(citasDTOReactiva1)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    /*@GetMapping("/citasReactivas/{idPaciente}/byidPaciente")
    private Flux<citasDTOReactiva> findAllByidPaciente(@PathVariable("idPaciente") String idPaciente) {
        return this.icitasReactivaService.findByIdPaciente(idPaciente);
    }*/


    @GetMapping(value = "/citasReactivas")
    private Flux<citasDTOReactiva> findAll() {
        return this.icitasReactivaService.findAll();
    }

    @PutMapping(value = "/citasReactivas/cancelar/{id}")
    private Mono<citasDTOReactiva> cancelarCita(@PathVariable("id") String id){
        return this.icitasReactivaService.cancelarCita(id);
                /*.flatMap(cita -> Mono.just(ResponseEntity.ok(cita)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));*/
    }

    @GetMapping(value = "/citasReactivas/{fechaReserva}/fecha")
    private Flux<citasDTOReactiva> findByFecha(@PathVariable("fechaReserva") String fecha){
        String[] date = fecha.split("-");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);
        LocalDate dateFormat = LocalDate.of(year, month, day);

        /*System.out.println(this.icitasReactivaService.findByFecha2(dateFormat));
        System.out.println(dateFormat);*/

        return this.icitasReactivaService.findByFecha(dateFormat);
        //return this.icitasReactivaService.findByFecha2(fecha);
    }

    @GetMapping(path = "/citasReactivas/{id}/consult-doctor/")
    private Mono<String> consultDoctor(@PathVariable("id") String id) {
        return this.icitasReactivaService.consultDoctor(id)
                .flatMap(cita -> Mono.just(cita.getNombreMedico() +" "+  cita.getApellidosMedico()));
    }

    @GetMapping(value = "/citasReactivas/{hora}/hora")
    private Flux<citasDTOReactiva> getCitasByHour(@PathVariable("hora") String hour) {
        return this.icitasReactivaService.findByHour(hour);
    }
    
    @GetMapping(path = "/citasReactivas/{fecha-hora}/fechaHora")
    private Flux<citasDTOReactiva> getByDateHour(@PathVariable("fecha-hora") String fechaHora) {
        String[] date = fechaHora.split("-");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);
        LocalDate dateFormat = LocalDate.of(year, month, day);
        String hour = date[3];

        return this.icitasReactivaService.findByFecha(dateFormat)
                .filter(cita -> cita.getHoraReservaCita().equals(hour));

    }
}
