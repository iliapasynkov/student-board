package com.epam.rdlab.online.service

import com.epam.rdlab.online.controller.Topic
import java.util.function.BiConsumer
import java.util.function.BinaryOperator
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Collector

object TopicCollectorFactory  {

    @JvmStatic
    fun getCollector() = object: Collector<Topic, MutableList<Topic>, MutableList<Topic>>{
        override fun supplier() = Supplier { mutableListOf<Topic>() }

        override fun accumulator() = BiConsumer { list: MutableList<Topic>, topic: Topic -> list.add(topic) }

        override fun combiner() = BinaryOperator { list1: MutableList<Topic>, list2: MutableList<Topic> -> list1.addAll(list2); list1 }

        override fun finisher() = Function.identity<MutableList<Topic>>()

        override fun characteristics(): Set<Collector.Characteristics> {
            return emptySet()
        }
    }
}