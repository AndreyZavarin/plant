package com.demo.services

import com.demo.dto.SubscriptionDto
import com.demo.models.Subscription

interface SubscriptionService : CrudService<Subscription, SubscriptionDto, Long>