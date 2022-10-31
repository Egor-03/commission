package by.grsu.anikevich.comission.tests;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SimpleTest {

		@BeforeAll
		static void setup() {
			System.out.println("@BeforeAll executed");
		}

		@BeforeEach
		void setupThis() {
			System.out.println("@BeforeEach executed");
		}

		@Test
		void testSaveSomething() {
			System.out.println("======saveSomething =======");
			boolean isSomethingSaved = true;
			Assertions.assertTrue(isSomethingSaved);
		}

		@Test
		void testUpdateSomething() {
			System.out.println("======updateSomething =======");
			boolean isSomethingUpdated = true;
			Assertions.assertTrue(isSomethingUpdated);
		}

		//@Disabled
		@Test
		void testDeleteSomething() {
			System.out.println("======deleteSomething=======");
			boolean isSomethingDeleted = false;
			Assertions.assertTrue(isSomethingDeleted);
		}

		@AfterEach
		void tearThis() {
			System.out.println("@AfterEach executed");
		}

		@AfterAll
		static void tear() {
			System.out.println("@AfterAll executed");
		}

	}


