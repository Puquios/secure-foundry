package com.ibm.ids.example.tests;

//********************************************************************************
// Copyright 2015 IBM
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//********************************************************************************

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ibm.ids.example.WordCountFinder;

/**
 * @author pskhadke
 *
 */
public class WordCountFinderTest {
	
	private WordCountFinder wcFinder;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.wcFinder = new WordCountFinder();
	}

	@Test
	public void testCountWords_Null() {
		int count = this.wcFinder.countWords(null);
		assertTrue(count == 0);
	}
	
	@Test
	public void testCountWords_EmptyString() {
		int count = this.wcFinder.countWords("");
		assertTrue(count == 0);
	}
	
	@Test
	public void testCountWords_Success() {
		String text = "Job Buck";
		
		int count = this.wcFinder.countWords(text);
		assertTrue(count == 2);
	}
}
