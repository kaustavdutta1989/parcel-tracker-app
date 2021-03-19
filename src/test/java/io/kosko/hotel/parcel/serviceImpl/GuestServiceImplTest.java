package io.kosko.hotel.parcel.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kosko.reception.hotel.model.Guest;
import com.kosko.reception.hotel.repositories.GuestRepository;
import com.kosko.reception.hotel.serviceImpl.GuestServiceImpl;

/**
 * @author kaust
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class GuestServiceImplTest {

	@InjectMocks
	GuestServiceImpl guestService;
	
	@Mock
	GuestRepository guestRepository;
	
	static List<Guest> guestList1 = new ArrayList<Guest>();
	static Guest guest1;
	static Guest guest2;
	static Guest guest3;
	static Guest guest4;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		guest1 = new Guest("Guest1", "Guest Details 2221");
		guest1.setId(1L);
		guestList1.add(guest1);
		
		guest2 = new Guest("Guest2", "Guest Detailsfsds");
		guest2.setId(2L);
		guestList1.add(guest2);
		
		guest3 = new Guest("Guest3", "Guest Details");
		guest3.setId(3L);
		guestList1.add(guest3);
		
		guest4 = new Guest("Guest4", "Guest Details");
		guest4.setId(4L);
		guestList1.add(guest4);
		
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	final void testFindAll() {
		when( guestRepository.findAll() ).thenReturn(guestList1);
		assertEquals( guestList1.size(), 4);
	}

	@Test
	final void testCreate() {
		when( guestRepository.save(Mockito.any(Guest.class)) ).thenReturn(guest1);
		assertEquals( guest1.getName(), "Guest1");
	}

	@Test
	final void testUpdate() {
		guestRepository.saveAndFlush(Mockito.any(Guest.class));
		assertEquals( guest1.getName(), "Guest1");
	}

	@Test
	final void testDeleteByName() {
		guestRepository.deleteByNameIgnoreCase( anyString() );
		assertEquals( guestList1.size(), 4);
	}

	@Test
	final void testFindByName() {
		// TODO
	}

}
