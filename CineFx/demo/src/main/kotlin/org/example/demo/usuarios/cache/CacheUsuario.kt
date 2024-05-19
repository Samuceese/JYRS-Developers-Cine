package org.example.demo.usuarios.cache

import org.example.demo.cache.CacheImpl
import org.example.demo.usuarios.models.Usuario

class CacheUsuario(size: Int): CacheImpl<String, Usuario>(size) {
}