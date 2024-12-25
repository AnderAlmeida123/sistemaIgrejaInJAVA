//package com.projeto.sistemaIgreja;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.vendor.Database;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class configuracaoBancoDeDados {
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver"); //declara as configuracao de acesso
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/igrejaProjeto");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("postgres");
//        return dataSource;
//
//
//
//    }
//
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//        adapter.setDatabase(Database.POSTGRESQL); //driver do banco
//        adapter.setShowSql(true); // mostra no console o sql
//        adapter.setGenerateDdl(true);
//       // adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
//        adapter.setPrepareConnection(true);
//        return adapter;
//    }
//
//}
