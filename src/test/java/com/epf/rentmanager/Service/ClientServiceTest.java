package com.epf.rentmanager.Service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

        @InjectMocks
        private ClientService clientService;

        @Mock
        private ClientDao clientDao;

        @Test
        void findAll_should_fail_when_dao_throws_exception() throws DaoException {
            // When
            when(this.clientDao.findAll()).thenThrow(DaoException.class);

            // Then
            assertThrows(ServiceException.class, () -> clientService.findAll());
        }
    }


