package org.example.demo.di



import org.example.demo.config.Config
import org.example.demo.database.SqlDelightManager
import org.example.demo.productos.butaca.cache.ButacasCacheImpl
import org.example.demo.productos.butaca.repositories.ButacaRepository
import org.example.demo.productos.butaca.repositories.ButacaRepositoryImpl
import org.example.demo.productos.butaca.services.ButacaService
import org.example.demo.productos.butaca.services.ButacaServiceImpl
import org.example.demo.productos.butaca.storage.ButacaStorage
import org.example.demo.productos.butaca.storage.ButacaStorageJsonImpl
import org.example.demo.productos.butaca.validator.ButacaValidator
import org.example.demo.productos.complementos.cache.ComplementoCacheImpl
import org.example.demo.productos.complementos.repositories.ComplementoRepository
import org.example.demo.productos.complementos.repositories.ComplementoRepositoryImpl
import org.example.demo.productos.complementos.services.ComplementoService
import org.example.demo.productos.complementos.services.ComplementoServiceImpl
import org.example.demo.productos.complementos.storage.ComplementoStorage
import org.example.demo.productos.complementos.storage.ComplementoStorageImpl
import org.example.demo.usuarios.cache.CacheUsuario
import org.example.demo.usuarios.repositories.UserRepository
import org.example.demo.usuarios.repositories.UserRepositoryImpl
import org.example.demo.usuarios.services.UserService
import org.example.demo.usuarios.services.UserServiceImpl
import org.example.demo.venta.repositories.VentasRepository
import org.example.demo.venta.repositories.VentasRepositoryImpl
import org.example.demo.venta.services.VentasService
import org.example.demo.venta.services.VentasServiceImpl
import org.example.demo.venta.storage.VentaStorageHtml
import org.example.demo.venta.storage.VentasStorage
import org.example.demo.view.viewModel.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val appModule = module {

    single<CacheUsuario> { CacheUsuario(5) }
    single<UserRepositoryImpl> { UserRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<UserService> { UserServiceImpl(get(), get()) }

    single<Config> { Config }

    single<SqlDelightManager> { SqlDelightManager(get()) }

    single<ButacaRepository> { ButacaRepositoryImpl(get()) }
    single<ButacaValidator> { ButacaValidator() }
    single<ButacasCacheImpl> { ButacasCacheImpl(5) }
    single<ButacaStorage> { ButacaStorageJsonImpl(get()) }
    single<ButacaServiceImpl> { ButacaServiceImpl(get(), get(), get(), get()) }
    single<ButacaService> { ButacaServiceImpl(get(), get(), get(), get()) }

    single<ComplementoRepository> { ComplementoRepositoryImpl(get()) }
    single<ComplementoCacheImpl> { ComplementoCacheImpl(5) }
    single<ComplementoStorage> { ComplementoStorageImpl() }
    single<ComplementoService> { ComplementoServiceImpl(get(), get(), get()) }

    single<VentasStorage> { VentaStorageHtml() }
    single<VentasRepository> { VentasRepositoryImpl(get(), get(), get(), get()) }
    single<VentasService> { VentasServiceImpl(get(), get(), get(), get()) }

    singleOf(::LoginViewModel)

    singleOf(::RegistroViewModel)

    singleOf(::OlvidarContraViewModel)

    singleOf(::SeleccionarAsientoViewModel)

    singleOf(::SeleccionarPeliculaViewModel)

    singleOf(::SeleccionarComplViewModel)

    singleOf(::CarritoViewModel)

    singleOf(::PagoViewModel)

    singleOf(::DetallesCompraViewModel)

    singleOf(::GestionButacaViewModel)

    singleOf(::ModificarButacaViewModel)

    singleOf(::GestionComplementoViewModel)

    singleOf(::NewComplementoViewModel)

    singleOf(::ActualizarComplViewModel)

    singleOf(::EstadoCineViewModel)
}

