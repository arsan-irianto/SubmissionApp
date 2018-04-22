package com.arsan.submissionapp.data.network

import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ApiRepositoryTest {

    @Test
    fun testDoRequest() {
        val api = mock(ApiRepository::class.java)
        val url_lastmatch = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328"
        api.doRequest(url_lastmatch)
        verify(api).doRequest(url_lastmatch)
    }
}