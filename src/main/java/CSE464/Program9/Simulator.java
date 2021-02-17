package CSE464.Program9;

import edu.princeton.cs.algs4.*;

public class Simulator
{
	private final static int N = 10;
	private static int collissions = 0;
	private static final double radMin = .01, radMax = .04;
	private static final double massMin = 1, massMax = 10;

	private static class Event implements Comparable<Event>
	{
		private final double time;
		private final Particle a, b;
		private final int countA, countB;

		public Event(double t, Particle a, Particle b)
		{  // Create a new event to occur at time t involving a and b.
			this.time = t;
			this.a    = a;
			this.b    = b;
			if (a != null) countA = a.count(); else countA = -1;
			if (b != null) countB = b.count(); else countB = -1;
		}

		public int compareTo(Event that)
		{
			if      (this.time < that.time) return -1;
			else if (this.time > that.time) return +1;
			else return 0;
		}

		public boolean isValid()
		{
			if (a != null && a.count() != countA) return false;
			if (b != null && b.count() != countB) return false;
			return true;
		}
	}


	private MinPQ<Event> pq;        // the priority queue
	private double t  = 0.0;        // simulation clock time
	private Particle[] particles;   // the array of particles

	public Simulator(Particle[] particles)
	{  this.particles  = particles;  }

	private void predictCollisions(Particle a, double limit)
	{
		if (a == null) return;
		for (int i = 0; i < particles.length; i++)
		{  // Put collision with particles[i] on pq.
			double dt = a.timeToHit(particles[i]);
			if (t + dt <= limit) {
				pq.insert(new Event(t + dt, a, particles[i]));
				collissions += 1;
			}
		}
		double dtX = a.timeToHitVerticalWall();
		if (t + dtX <= limit)
			pq.insert(new Event(t + dtX, a, null));
		double dtY = a.timeToHitHorizontalWall();
		if (t + dtY <= limit)
			pq.insert(new Event(t + dtY, null, a));
	}


	public void redraw(double limit, double Hz)
	{ // Redraw event: redraw all particles.
		StdDraw.clear();
		for (int i = 0; i < particles.length; i++) particles[i].draw();
		StdDraw.show(20);
		if (t < limit)
			pq.insert(new Event(t + 1.0 / Hz, null, null));
	}

	public void simulate(double limit, double Hz)
	{
		pq = new MinPQ<Event>();
		for (int i = 0; i < particles.length; i++)
			predictCollisions(particles[i], limit);
		pq.insert(new Event(0, null, null));  // Add redraw event.

		while (!pq.isEmpty())
		{  // Process one event to drive simulation.
			Event event = pq.delMin();
			if (!event.isValid()) continue;
			for (int i = 0; i < particles.length; i++)
				particles[i].move(event.time - t); // Update particle positions
			t = event.time;                       //   and time.
			Particle a = event.a, b = event.b;
			if      (a != null && b != null) a.bounceOff(b);
			else if (a != null && b == null) a.bounceOffVerticalWall();
			else if (a == null && b != null) b.bounceOffHorizontalWall();
			else if (a == null && b == null) redraw(limit, Hz);
			predictCollisions(a, limit);
			predictCollisions(b, limit);
		}
	}


	public static void main(String[] args)
	{
		StdDraw.show(0);
		Particle[] particles = new Particle[N];
		for (int i = 0; i < N; i++)
			particles[i] = new Particle(radMin, radMax, massMin, massMax);
		Simulator system = new Simulator(particles);
		system.simulate(10000, 0.5);
	}
}