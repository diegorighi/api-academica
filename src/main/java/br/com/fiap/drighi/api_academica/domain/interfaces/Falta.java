package br.com.fiap.drighi.api_academica.domain.interfaces;

import java.time.LocalDateTime;

public interface Falta<T> {

    public T getSujeito();
    public LocalDateTime getData();
}
