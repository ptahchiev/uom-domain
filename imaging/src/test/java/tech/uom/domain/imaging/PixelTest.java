/*
 * Domain Specific Units of Measurement Extensions
 * Copyright (c) 2018, Units of Measurement
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 *    and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of Unit-API, Units of Measurement nor the names of their contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package tech.uom.domain.imaging;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import javax.measure.MetricPrefix;
import javax.measure.IncommensurableException;
import javax.measure.UnconvertibleException;
import javax.measure.quantity.Length;

import org.junit.jupiter.api.Test;

import systems.uom.quantity.Resolution;
import tech.units.indriya.ComparableQuantity;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

public class PixelTest {

	@Test
	public void testPPItoMetreConversion() throws UnconvertibleException, IncommensurableException {
		/*
		 * 960 Pixel are .254 m long if the PPI is 96. 1 inch (0.0254 m) / 96 ppi x (96
		 * x 10) px = 0.254 m
		 */
		final ComparableQuantity<Length> screenWidth = Quantities.getQuantity(960, Imaging.PIXEL);
		final ComparableQuantity<Resolution> screenResolution = Quantities.getQuantity(96, Imaging.PIXEL_PER_INCH);

		ComparableQuantity<Length> ppi = screenWidth.divide(screenResolution).asType(Length.class);
		ComparableQuantity<Length> pixelToMetre = ppi.to(Units.METRE);
		// TODO change dependency to something else
		assertThat(pixelToMetre.getValue().doubleValue(), is(.254));
		assertThat(pixelToMetre.to(MetricPrefix.CENTI(Units.METRE)).getValue().doubleValue(), is(25.4));
	}
}
