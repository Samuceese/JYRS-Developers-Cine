package org.example.demo.di



import org.example.demo.productos.butaca.cache.ButacasCacheImpl
import org.example.demo.productos.butaca.repositories.ButacaRepository
import org.example.demo.productos.butaca.repositories.ButacaRepositoryImpl
import org.example.demo.productos.butaca.services.ButacaService
import org.example.demo.productos.butaca.services.ButacaServiceImpl
import org.example.demo.productos.butaca.storage.ButacaStorage
import org.example.demo.productos.butaca.storage.ButacaStorageImpl
import org.example.demo.productos.butaca.validator.ButacaValidator
import org.example.demo.usuarios.cache.CacheUsuario
import org.example.demo.usuarios.repositories.UserRepository
import org.example.demo.usuarios.repositories.UserRepositoryImpl
import org.example.demo.usuarios.services.UserService
import org.example.demo.usuarios.services.UserServiceImpl
import org.example.demo.view.controllers.*
import org.example.demo.view.viewModel.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val appModule = module {
    single <CacheUsuario>{ CacheUsuario(5) }
    single <UserRepository>{ UserRepositoryImpl() }
    single <UserService>{ UserServiceImpl(get(),get()) }
    single <ButacaRepository>{ ButacaRepositoryImpl() }
    single <ButacaValidator>{ ButacaValidator()  }
    single <ButacasCacheImpl>{ ButacasCacheImpl(5) }
    single <ButacaStorage>{ ButacaStorageImpl(get()) }
    single <ButacaService>{ ButacaServiceImpl(get(),get(),get(),get()) }

    //singleOf(:: LoginViewController)
    singleOf(:: LoginViewModel)
    //singleOf(:: RegistroViewController)
    singleOf(:: RegistroViewModel)
    //singleOf(:: OlvidarContraViewController)
    singleOf(:: OlvidarContraViewModel)
    singleOf(:: SeleccionarAsientoViewModel)
    //singleOf(:: SeleccionarAsientoViewController)

    singleOf(:: SeleccionarPeliculaViewModel)
   // singleOf(:: SeleccionarAsientoViewController)

}

