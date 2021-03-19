package io.kosko.hotel.parcel.serviceImpl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kosko.reception.hotel.model.Guest;
import com.kosko.reception.hotel.model.Parcel;
import com.kosko.reception.hotel.repositories.GuestRepository;
import com.kosko.reception.hotel.repositories.ParcelRepository;
import com.kosko.reception.hotel.serviceImpl.ParcelServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

/**
 * @author kaust
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ParcelServiceImplTest {

	@InjectMocks
	ParcelServiceImpl parcelService;
	
	@Mock
	ParcelRepository parcelRepository;
	
	@Mock
	GuestRepository guestRepository;
	
	static Set<Parcel> parcelList1 = new HashSet<Parcel>();
	static Parcel parcel1;
	static Parcel parcel2;
	static Set<Parcel> parcelList2 = new HashSet<Parcel>();
	static Parcel parcel3;
	static Parcel parcel4;

	static List<Parcel> parcelList3 = new ArrayList<Parcel>();
	
	static List<Guest> guestList1 = new ArrayList<Guest>();
	static Guest guest1;
	static Guest guest2;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		guest1 = new Guest("Guest1", "Guest Details 2221");
		guest1.setId(1L);
		guestList1.add(guest1);
		
		guest2 = new Guest("Guest2", "Guest Detailsfsds");
		guest2.setId(2L);
		guestList1.add(guest2);
		
		parcel1 = new Parcel("PARCEL_01");
		parcel1.setId(3L);
		parcelList1.add(parcel1);
		
		parcel2 = new Parcel("PARCEL_02");
		parcel2.setId(3L);
		parcelList1.add(parcel2);
		guest1.setParcels(parcelList1);

		parcel3 = new Parcel("PARCEL_03");
		parcel3.setId(3L);
		parcelList2.add(parcel3);
		
		parcel4 = new Parcel("PARCEL_04");
		parcel4.setId(3L);
		parcelList2.add(parcel4);
		guest2.setParcels(parcelList2);
		
		parcelList3.addAll(parcelList1);
		parcelList3.addAll(parcelList2);
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	final void testFindAll() {
		when( parcelRepository.findAll() ).thenReturn(parcelList3);
		assertEquals(parcelList3.size(), 4);
	}

	@Test
	final void testFindByCode() {
		when( parcelRepository.findByCodeIgnoreCase( anyString() ) ).thenReturn(parcel1);
		assertEquals( parcel1.getCode(), "PARCEL_01");
	}

	@Test
	final void testCreate() {
		when( parcelRepository.save(Mockito.any(Parcel.class)) ).thenReturn(parcel1);
		assertEquals( parcel1.getCode(), "PARCEL_01");
	}

	@Test
	final void testUpdateParcel() {
		// TODO
	}

	@Test
	final void testDeleteByCode() {
		// TODO
	}

	@Test
	final void testParcelPickedByGuest() {
		// TODO
	}

	@Test
	final void testUpdateAllPickedupParcel() {
		// TODO
	}

}
