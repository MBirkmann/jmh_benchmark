package com.benchmark.jmh;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Benchmark)
public class JMHListToSet {
	int size = 10000;
	List<Person> persons = new ArrayList<>(size);
	Random random;

	@Setup(Level.Trial)
	public void init() {

		for (int i = 0; i < size; i++) {
			Person person = new Person();
			Random random = new Random();
			UUID id = UUID.randomUUID();
			String forename = String.valueOf(random.nextInt());
			String lastname = String.valueOf(random.nextInt());
			person.setId(id);
			person.setForename(forename);
			person.setLastname(lastname);

			persons.add(person);
		}
	}

	@Benchmark
	public void classicForLoop() {
		Set<UUID> keyset = new HashSet<>();

		for (int i = 0; i < persons.size(); i++) {
			UUID personId = persons.get(i).getId();
			keyset.add(personId);
		}
	}

	@Benchmark
	public void forEachLoop() {
		Set<UUID> keyset = new HashSet<>();

		for (Person person : persons) {
			UUID personId = person.getId();
			keyset.add(personId);
		}
	}

	@Benchmark
	public void streamMethodReference() {
		persons.stream().map(Person::getId).collect(Collectors.toSet());
	}

	@Benchmark
	public void streamLambda() {
		persons.stream().map(p -> p.getId()).collect(Collectors.toSet());
	}

	@Benchmark
	public void parallelStreamMethodReference() {
		persons.parallelStream().map(Person::getId).collect(Collectors.toSet());
	}

	@Benchmark
	public void parallelStreamLambda() {
		persons.parallelStream().map(p -> p.getId()).collect(Collectors.toSet());
	}

	public static void main(String[] args) throws RunnerException {
		int processors = Runtime.getRuntime().availableProcessors();
		System.out.println("Number of processors: " + processors);
		Options options = new OptionsBuilder().include(JMHListToSet.class.getSimpleName()).threads(processors).forks(1)
				.shouldFailOnError(true).shouldDoGC(true).jvmArgs("-server").build();
		new Runner(options).run();

	}

	class Person {
		private UUID id;
		private String forename;
		private String lastname;

		public UUID getId() {
			return id;
		}

		public void setId(UUID id) {
			this.id = id;
		}

		public String getForename() {
			return forename;
		}

		public void setForename(String forename) {
			this.forename = forename;
		}

		public String getLastname() {
			return lastname;
		}

		public void setLastname(String lastname) {
			this.lastname = lastname;
		}

	}
}
